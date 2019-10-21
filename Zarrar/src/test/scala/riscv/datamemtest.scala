package riscv

import chisel3.iotesters.PeekPokeTester

class datamemtest(c: datamem) extends PeekPokeTester(c){

	//poke(c.io.address,1)
	//poke(c.io.data,1)
	//poke(c.io.strsel,1)
	//poke(c.io.ldsel,0)
	step(1)
	//expect(c.io.out,1)
}
