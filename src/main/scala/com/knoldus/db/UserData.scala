package com.knoldus.db
import com.knoldus.Models.User
import com.knoldus.bootstrap.MainClass.updatedUser

import java.util.UUID
import scala.collection.mutable.ListBuffer
import scala.math.Ordered.orderingToOrdered


class UserData extends  dao[User] {

  private val listBuffer = new ListBuffer[User]()

  override def add(user: User): UUID = {
    val uuid = UUID.randomUUID()

    user match {
      case user: User => listBuffer += user.copy(id=Some(uuid))
        uuid

      case User(Some(_), _, _, _, _, _, _, _) => throw new RuntimeException("Invalid operations")


    }
  }

  override def getById(id: UUID): List[User] = {
    val list = filterListById(id)

    if (list != Nil) list else throw new NoSuchElementException("user not exist")
  }

  override def getAll: List[User] = {

    if (listBuffer.toList != Nil) listBuffer.toList else throw new NoSuchElementException("No user found")
  }

  override  def update(id: UUID, user: User): Boolean = {
    val index = findIndexById(id)
    if (index != -1) {
      listBuffer.update(index, updatedUser)
      true
    } else false
  }


  override def delete(id: UUID): Boolean = {
    val index = findIndexById(id)
    if (index != -1) {
      listBuffer.remove(index); true
    } else false

  }

  override  def deleteAll(): Boolean = {
    if (listBuffer.nonEmpty) { listBuffer.remove(0, listBuffer.length); true} else false

  }


  def filterListById(id:UUID): List[User] = {
    listBuffer.filter(listBuffer => if(listBuffer.id.compareTo(Some(id)) == 0) true else false).toList
  }

  def findIndexById(id:UUID): Int = {
    val list = filterListById(id)
    if(list != Nil) listBuffer.indexOf(list.head) else -1
  }


}
