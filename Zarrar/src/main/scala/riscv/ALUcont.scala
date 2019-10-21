package riscv

import chisel3._
import chisel3.util.Cat

class ALUcont extends Module {
	val io = IO(new Bundle{
	val ALUop = Input(UInt(3.W))
	val func3 = Input(UInt(3.W))
	val func7 = Input(UInt(1.W))
	val ALUcont = Output(UInt(5.W))
})
	when(io.ALUop === "b000".U){ //R
		io.ALUcont := Cat("b0".U,io.func7,io.func3)
	}.elsewhen(io.ALUop === "b001".U ){ // I
		when(io.func3 === "b101".U){
			io.ALUcont := Cat("b0".U,io.func7,io.func3)
		}.otherwise{
			io.ALUcont := Cat("b00".U,io.func3)
		}
		//io.ALUcont := Cat("b0".U,io.func7,io.func3)
	}.elsewhen(io.ALUop ==="b101".U || io.ALUop ==="b100".U){ // S and I
		io.ALUcont := "b00000".U
	}.elsewhen(io.ALUop ==="b011".U ){ // jal and jalr
		io.ALUcont := "b11111".U
	}.elsewhen(io.ALUop ==="b010".U ){ //SB
		io.ALUcont := Cat("b10".U,io.func3)
	}.otherwise{
		io.ALUcont := DontCare
	}

	



}
