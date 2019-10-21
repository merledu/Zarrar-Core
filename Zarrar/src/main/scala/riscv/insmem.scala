package riscv
import chisel3._
import chisel3.util.experimental.loadMemoryFromFile

class insmem extends Module {
    val io = IO(new Bundle {
	val address = Input(UInt(32.W)) 
	val data = Output(UInt(32.W))
    })
	val mem = Mem(1024,UInt(32.W))
	io.data := mem(io.address)
	loadMemoryFromFile(mem,"/home/fizza/textfile.txt")
}
