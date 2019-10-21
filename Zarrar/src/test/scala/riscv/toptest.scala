package riscv

import chisel3.iotesters.PeekPokeTester

class toptest(c: top) extends PeekPokeTester(c){

	step(5)
}
