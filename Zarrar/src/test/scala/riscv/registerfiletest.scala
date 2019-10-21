package riscv
import chisel3.iotesters.PeekPokeTester
class registerfiletest(c: registerfile) extends PeekPokeTester(c) {
	poke(c.io.rs1s,3)
	poke(c.io.rs2s,2)
	poke(c.io.regwrite,1)
	poke(c.io.rd,4)
	poke(c.io.writedata,4)
	step(1)
	expect(c.io.rs2,0)
	expect(c.io.rs1,0)
	

}






