package com.example.usertodolist.repositery



class UserTodoListRepository() {


//    val todolist:LiveData<List<UserTodoResponse>> = Transformations.map(database.todoRoomDao.getUserList()){
//        it.asDomainModel()
//    }
//
//    val  filterList:LiveData<List<UserTodoResponse>> = Transformations.map(database.todoRoomDao.getTodo(4228)){
//        it.asDomainModel()
//    }
//
//     fun refrestTodoList(){
//        val todoList = UserTodoApiConnect.retrofitService.getTodoList()
//        val list = todoList.body()
//         println("\n\n\n\n${list.toString()}\n\n\n")
//      if(todoList.isSuccessful){
//          if (list != null) {
//              database.todoRoomDao.insertAll(*list.asDatabaseModel())
//          }
//      }
//    }
}