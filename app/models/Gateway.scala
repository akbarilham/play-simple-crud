package models

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.MySQLProfile.api._

case class Gateway(id: Long, name: String, isComplete: Boolean)

case class GatewayFormData(name: String, isComplete: Boolean)

object GatewayForm {
    val form = Form(
        mapping(
            "name" -> nonEmptyText,
            "isComplete" -> boolean
        ) (GatewayFormData.apply)(GatewayFormData.unapply)
    )
}

class GatewayTableDef(tag: Tag) extends Table[Gateway](tag, "gateway") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def isComplete = column[Boolean]("isComplete")
    override def * = (id, name, isComplete) <> (Gateway.tupled, Gateway.unapply)
}

class GatewayList @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile]{
    var gatewayList = TableQuery[GatewayTableDef]

    def get(id: Long): Future[Option[Gateway]] = {
        dbConfig.db.run(gatewayList.filter(_.id === id).result.headOption)
    }

    def listAll: Future[Seq[Gateway]] = {
        dbConfig.db.run(gatewayList.result)
    }

    def add(gatewayItem: Gateway): Future[String] = {
        dbConfig.db
        .run(gatewayList += gatewayItem)
        .map(res => "GatewayItem insert successfully")
        .recover {
            case ex: Exception => {
                printf(ex.getMessage())
                ex.getMessage
            }
        }
    }

    def update(gatewayItem: Gateway): Future[Int] = {
        dbConfig.db
        .run(gatewayList.filter(_.id === gatewayItem.id)
            .map(x => (x.name, x.isComplete))
            .update(gatewayItem.name, gatewayItem.isComplete)
        )
    }

    def delete(id: Long): Future[Int] = {
        dbConfig.db.run(gatewayList.filter(_.id === id).delete)
    }
}