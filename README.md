# Zarrar, A Single Cycle Core on RISC-V Implementation
 
Designed by Syeda Fizza Jaffery

First of all get started by cloning this repository on your machine.

git clone https://github.com/merledu/Zarrar-Core.git

Create a .txt file and place the hexadecimal code of your instructions simulated on Venus (RISC-V Simulator)
Each instruction's hexadecimal code must be on seperate line as following. This program consists of 9 instructions.

00500113
00500193
014000EF
00120293
00502023
00002303
00628663
00310233
00008067

Then perform the following step

cd riscv/Zarrar/src/main/scala/riscv

Open insmem.scala with this command. You can also manually go into the above path and open the file in your favorite text editor.

open insmem.scala

Find the following line

loadMemoryFromFile(mem,"/home/fizza/textfile.txt")

Change the .txt file path to match your file that you created above storing your own program instructions.
After setting up the insmem.scala file, go inside the Zarrar folder.

cd riscv/Zarrar

And enter

sbt

When the terminal changes to this type

sbt:Zarrar>

Enter this command

sbt:Zarrar> test:runMain riscv.Launcher top

After you get success

sbt:Zarrar> test:runMain riscv.Launcher Top --backend-name verilator

After success you will get a folder test_run_dir on root of your folder. Go into the examples folder inside.
There you will find the folder named top. Enter in it and you can find the top.vcd file which you visualise on gtkwave to
see your program running.
