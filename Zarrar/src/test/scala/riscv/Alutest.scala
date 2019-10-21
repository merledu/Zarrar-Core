package riscv
import chisel3.iotesters.PeekPokeTester
class Alutest(c:Alu) extends PeekPokeTester(c) {
	poke(c.io.AluControl, 0)
	poke(c.io.in1, 2)
	poke(c.io.in2, 3)
	step(1)
	expect(c.io.Branch,0 )
	expect(c.io.out,5 )
}
