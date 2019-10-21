package riscv
import chisel3._
class top extends Module {
    val io = IO(new Bundle {
	//val instruction = Input(UInt(32.W))
	//val pc = Input(UInt(32.W))
	val output = Output(SInt(32.W))
    })
	val pc = Module (new pc())
	val insmem = Module (new insmem())
	val control = Module (new Control())
	val immediategen = Module (new ImmGen())
	val regfile = Module (new registerfile())
	val alucontrol = Module (new ALUcont())
	val jalr = Module (new jalr())
	val alu = Module (new Alu())
	val dataMem = Module(new datamem())

	pc.io.input := pc.io.pc4

	insmem.io.address := pc.io.pcout(11,2)

	control.io.opcode := insmem.io.data(6,0)
	
	immediategen.io.pc := pc.io.pcout
	immediategen.io.instruction := insmem.io.data

	regfile.io.regwrite := control.io.RegWrite
	regfile.io.rd := insmem.io.data(11,7)
	regfile.io.rs1s := insmem.io.data(19,15)
	regfile.io.rs2s := insmem.io.data(24,20)

	alucontrol.io.ALUop := control.io.AluOp
	alucontrol.io.func3 :=insmem.io.data(14,12)
	alucontrol.io.func7 :=insmem.io.data(30)

	//jalr
	when (control.io.OpB === 1.U){
		jalr.io.a  := immediategen.io.i_imm
	}.otherwise{
		jalr.io.a := DontCare
	}
	jalr.io.b := regfile.io.rs1

	// Alu Connection
	alu.io.AluControl := alucontrol.io.ALUcont

	//upper mux into alu
	when (control.io.OpA === 0.U){
		alu.io.in1 := regfile.io.rs1
	}.elsewhen(control.io.OpA === 2.U){
		alu.io.in1 := pc.io.pc4.asSInt()
	}.elsewhen(control.io.OpA === 3.U){
		alu.io.in1 := regfile.io.rs1
	}.otherwise{
		alu.io.in1 := DontCare
	}

	//lower mux into alu
	when(control.io.OpB === 0.U){
		alu.io.in2 := regfile.io.rs2
	}.elsewhen(control.io.OpB === 1.U){
		//mux after immgen
		when( control.io.ExtSel === 0.U){
			alu.io.in2 := immediategen.io.i_imm
		}.elsewhen( control.io.ExtSel === 1.U){
				alu.io.in2 := immediategen.io.u_imm
		}.elsewhen( control.io.ExtSel === 2.U){
			alu.io.in2 := immediategen.io.s_imm
		}.otherwise {
			alu.io.in2 := DontCare
		}
	}.otherwise{
		alu.io.in2 := DontCare
	}

	// DataMemory Connection
	dataMem.io.address := (alu.io.out(9,2)).asUInt
	dataMem.io.storeData := regfile.io.rs2
	dataMem.io.store := control.io.MemWrite
	dataMem.io.load := control.io.MemRead
	//Mux after Data Memory
	when(control.io.MemtoReg === 0.U){
		regfile.io.writedata := alu.io.out
	}.elsewhen(control.io.OpB === 1.U){
		regfile.io.writedata := dataMem.io.dataOut
	}.otherwise{
		regfile.io.writedata := DontCare
	}

	//top mux
	
	when (control.io.NextPc === 0.U){
		pc.io.input := pc.io.pc4
	}.elsewhen(control.io.NextPc === 1.U){
		when ( (alu.io.Branch & control.io.Branch) === 0.U ){
			pc.io.input := pc.io.pc4
		}.elsewhen( (alu.io.Branch & control.io.Branch) === 1.U){
			pc.io.input := immediategen.io.sb_imm.asUInt
		}.otherwise {
			pc.io.input := DontCare
		}
	}.elsewhen (control.io.NextPc === 2.U){
		pc.io.input := immediategen.io.uj_imm.asUInt
	}.elsewhen(control.io.NextPc === 3.U){
		pc.io.input:= jalr.io.o.asUInt()
	}.otherwise{
		pc.io.input:= DontCare
	}

	io.output := alu.io.out

}
