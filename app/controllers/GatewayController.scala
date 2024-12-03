package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import play.api.http.Status
import models.{Gateway, GatewayForm}
import play.api.data.FormError

import services.GatewayService
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class GatewayController @Inject()(cc: ControllerComponents, gatewayService: GatewayService) extends AbstractController(cc) {
    implicit val gatewayFormat = Json.format[Gateway]
    
    def getAll() = Action.async { implicit request: Request[AnyContent] =>
        gatewayService.listAllItems map { items =>
            Ok(Json.toJson(items))
        }
    }

    def getById(id: Long) = Action.async { implicit request: Request[AnyContent] =>
        gatewayService.getItem(id) map { item =>
            Ok(Json.toJson(item))
        }
    }

    def add() = Action.async { implicit request: Request[AnyContent] =>
        GatewayForm.form.bindFromRequest().fold(
            errorForm => {
                errorForm.errors.foreach(println)
                Future.successful(BadRequest(Json.obj("status" -> 400, "message" -> "Insert item has failed - Bad Request")))
            },
            data => {
                val newGatewayItem = Gateway(0, data.name, data.isComplete)
                gatewayService.addItem(newGatewayItem).map( _ => Ok(Json.obj("status" -> 200, "message" -> "Insert item has succesfully")))
            }
        )
    }

    def update(id: Long) = Action.async { implicit request: Request[AnyContent] =>
        GatewayForm.form.bindFromRequest().fold(
            errorForm => {
                errorForm.errors.foreach(println)
                Future.successful(BadRequest(Json.obj("status" -> 400, "message" -> "Update item has failed - Bad Request")))
            },
            data => {
                val gatewayItem = Gateway(id, data.name, data.isComplete)
                gatewayService.updateItem(gatewayItem).map( _ => Ok(Json.obj("status" -> 200, "message" -> "Update item has succesfully")))
            }
        )
    }

    def delete(id: Long) = Action.async { implicit request: Request[AnyContent] =>
        gatewayService.deleteItem(id) map { res =>
            Ok(Json.obj("status" -> 200, "message" -> "Delete item has succesfully"))
        }
    }

    def pageCalc(page: Int, pageSize: Int, totalItems: Int) = {
        val from = ((page - 1) * pageSize) + 1
        val to = totalItems min (from + pageSize - 1)
        val totalPages = (totalItems / pageSize) + (if (totalItems % pageSize > 0) 1 else 0)
        (from, to, totalPages)
    }

}