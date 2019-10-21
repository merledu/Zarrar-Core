package riscv

import chisel3.iotesters.{Driver, TesterOptionsManager}
import utils.TutorialRunner

object Launcher {
  val examples = Map(
	"Control" -> { (manager: TesterOptionsManager) =>
		Driver.execute(() => new Control(), manager) {
		  (c) => new ControlTests(c)
		}
	},
	"InstTypeDeco" -> { (manager: TesterOptionsManager) =>
		Driver.execute(() => new InstTypeDeco(), manager) {
		  (c) => new InstTypeDecoTests(c)
		}
	},

	"ALUcont" -> { (manager: TesterOptionsManager) =>
		Driver.execute(() => new ALUcont(), manager) {
		  (c) => new ALUconttest(c)
		}
	},
	"registerfile" -> { (manager: TesterOptionsManager) =>
		Driver.execute(() => new registerfile(), manager) {
		  (c) => new registerfiletest(c)
		}
	},
	"ImmGen" -> { (manager: TesterOptionsManager) =>
		Driver.execute(() => new ImmGen(), manager) {
		  (c) => new ImmGenTests(c)
		}
	},
	"Alu" -> { (manager: TesterOptionsManager) =>
		Driver.execute(() => new Alu(), manager) {
		  (c) => new Alutest(c)
		}
	},
	"top" -> { (manager: TesterOptionsManager) =>
		Driver.execute(() => new top(), manager) {
		  (c) => new toptest(c)
		}
	},
	"pc" -> { (manager: TesterOptionsManager) =>
		Driver.execute(() => new pc(), manager) {
		  (c) => new pctest(c)
		}
	},
	"insmem" -> { (manager: TesterOptionsManager) =>
		Driver.execute(() => new insmem(), manager) {
		  (c) => new insmemtest(c)
		}
	},
	"jalr" -> { (manager: TesterOptionsManager) =>
		Driver.execute(() => new jalr(), manager) {
		  (c) => new jalrtest(c)
		}
	},
		"datamem" -> { (manager: TesterOptionsManager) =>
		Driver.execute(() => new datamem(), manager) {
		  (c) => new datamemtest(c)
		}
	},
	"CntrlDecode" -> { (manager: TesterOptionsManager) =>
		Driver.execute(() => new CntrlDecode(), manager) {
		  (c) => new CntrlDecodeTests(c)
		}
	}
)
  def main(args: Array[String]): Unit = {
    TutorialRunner("examples", examples, args)
  }
}



