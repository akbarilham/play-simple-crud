package services

import com.google.inject.Inject
import models.{Gateway, GatewayList}
import scala.concurrent.Future

class GatewayService @Inject() (items: GatewayList) {
    def getItem(id: Long): Future[Option[Gateway]] = {
        items.get(id)
    }

    def listAllItems: Future[Seq[Gateway]] = {
        items.listAll
    }

    def addItem(item: Gateway): Future[String] = {
        items.add(item)
    }

    def updateItem(item: Gateway): Future[Int] = {
        items.update(item)
    }

    def deleteItem(id: Long): Future[Int] = {
        items.delete(id)
    }
}