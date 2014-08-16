/**
 * 
 */
 function TodoCtrl($scope, $http) {
  $scope.todos = [
    {text:'learn angular', done:true},
    {text:'build an angular app', done:false}];
  
  $scope.addTodo = function() {
    $scope.todos.push({text:$scope.todoText, done:false});
    $scope.todoText = '';
  };
  
  $scope.getCustomer = function(){
	  $http.get('http://localhost:8080/PersonalFinancialServices/.json').success(function(data){
	        console.log(data);
	        $scope.value = data;
	    }); 
  };
  $scope.postCustomer = function(){
	  $http.post('http://localhost:8080/PersonalFinancialServices/', $scope.value).success(function(data){
	        console.log(data);
	        $scope.value = data;
	    }); 
  };
  $scope.remaining = function() {
    var count = 0;
    angular.forEach($scope.todos, function(todo) {
      count += todo.done ? 0 : 1;
    });
    return count;
  };
 
  $scope.archive = function() {
    var oldTodos = $scope.todos;
    $scope.todos = [];
    angular.forEach(oldTodos, function(todo) {
      if (!todo.done) $scope.todos.push(todo);
    });
  };
}