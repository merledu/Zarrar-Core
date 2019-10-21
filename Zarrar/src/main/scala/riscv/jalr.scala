package riscv
import chisel3._

class jalr extends Module {
  val io = IO(new Bundle {
	val a = Input(SInt(32.W))
	val b = Input(SInt(32.W))
	val o = Output(SInt(32.W))
})
	val add = io.a + io.b
	io.o := add & 4294967294L.S
}
