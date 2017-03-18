package controllers

import javax.inject._

import play.api._
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.libs.json.{Json, Writes}
import play.api.mvc._
import util.MathParser
import views.html.helper.input

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller with MathParser{


  case class ParseRet(parse: String)
  implicit lazy val todoViewWrite = new Writes[ParseRet] {
    def writes(pa: ParseRet) =
      Json.obj(
        "parse" -> pa.parse
      )
  }

  case class EditInput(inputText: String)
  implicit val inputForm:Form[EditInput] = Form (
    mapping(
      "input" -> text
    )(EditInput.apply)(EditInput.unapply)
  )

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def math_parse = Action {implicit request =>
    val edInput = inputForm.bindFromRequest.get
    //Ok(Json.toJson(ParseRet(mathParse(edInput.inputText))))
    val parse = mathParse(edInput.inputText)
    Ok(Json.toJson(ParseRet(parse.toString)))
  }

}
