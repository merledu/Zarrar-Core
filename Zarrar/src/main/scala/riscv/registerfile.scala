package riscv
import chisel3._
class registerfile extends Module {
    val io = IO(new Bundle {
	val regwrite = Input(UInt(1.W))
	val rd = Input(UInt(5.W))
	val rs1s = Input(UInt(5.W))
	val rs2s = Input(UInt(5.W))
	val writedata = Input(SInt(32.W))
	val rs1 = Output(SInt(32.W))
	val rs2 = Output(SInt(32.W))
    })
    	val register = Reg(Vec(32, SInt(32.W)))
    	register(0) := 0.S
    	io.rs1 := register(io.rs1s)
    	io.rs2 := register(io.rs2s)
    	when(io.regwrite === 1.U) {
				when(io.rd === "b00000".U){
					register(0) := 0.S
				}.otherwise{
					register(io.rd) := io.writedata
				}
    	}
}
