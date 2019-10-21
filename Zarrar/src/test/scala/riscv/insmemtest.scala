package riscv

import chisel3.iotesters.PeekPokeTester

class insmemtest(c: insmem) extends PeekPokeTester(c){

	poke(c.io.address,5)
	step(1)
}
