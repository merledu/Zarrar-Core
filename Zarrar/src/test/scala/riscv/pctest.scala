package riscv

import chisel3.iotesters.PeekPokeTester

class pctest(c: pc) extends PeekPokeTester(c){

	poke(c.io.input,0)
	step(1)
}
