package util

import util.Element.elem

class ExprFormatter {
  private val fractionPrecedence = -1

  private def format(e: Expression, enclPrec: Int): Element =
    e match {
      case Value(num) =>
        elem("  " + num.toString + "  ")
      case Add(x, y) =>
        val l = format(x, fractionPrecedence)
        val op = elem(" + ")
        val r = format(y, fractionPrecedence)
        l beside op beside r
      case Sub(exp1, exp2) =>
        val l = format(exp1, fractionPrecedence)
        val op = elem(" - ")
        val r = format(exp2, fractionPrecedence)
        l beside op beside r
      case Plus(exp) =>
        val op = elem(" + ")
        val ex = format(exp, fractionPrecedence)
        op beside ex
      case Minus(exp) =>
        val op = elem(" - ")
        val ex = format(exp, fractionPrecedence)
        op beside ex
      case Parenthesized(exp) =>
        val l = elem("( ")
        val ex = format(exp)
        val r = elem(" )")
        l beside ex beside r
      case Multiply(exp1, exp2) =>
        val l = format(exp1, fractionPrecedence)
        val op = elem(" x ")
        val r = format(exp2, fractionPrecedence)
        l beside op beside r
      case Divide(exp1, exp2) =>
        val top = format(exp1, fractionPrecedence)
        val bot = format(exp2, fractionPrecedence)
        val line = elem('-', (top.width) max (bot.width ), 1)
        val frac = top above line above bot
        if (enclPrec != fractionPrecedence) frac
        else frac
      case ExecResult(exp, result) =>
        val e = format(exp, fractionPrecedence)
        val eq = elem(" = ")
        val r = format(result, fractionPrecedence)
        e beside eq beside r
    }
  def format(e: Expression): Element = format(e, 0)
}

trait MathParser {
  def mathParse(input: String) = {
    val f: ExprFormatter = new ExprFormatter
    val parser = new ExprParser
    val parseResult = parser.parse(input)

    if ( parseResult.successful ){
      val evalResult = parseResult.get.accept(new Evaluator)
      f.format(ExecResult(parseResult.get, Value(evalResult)))
    }else{
      ""
    }
  }
}
