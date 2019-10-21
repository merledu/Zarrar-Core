package riscv

import chisel3.iotesters.PeekPokeTester
class ALUconttest(c:ALUcont) extends PeekPokeTester(c) {
	poke(c.io.ALUop, 1)
	poke(c.io.func7, 0)
	poke(c.io.func3, 0)
	step(1)
	expect(c.io.ALUcont,0)


}
