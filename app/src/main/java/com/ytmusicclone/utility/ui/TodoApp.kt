//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import ytmusicclone.viewmodels.TodoViewModel

//package com.example.ytmusicclone
//
//import android.annotation.SuppressLint
//import android.util.Log
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.focusGroup
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScope
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyItemScope
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowForward
//import androidx.compose.material.icons.filled.Notifications
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Checkbox
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.focus.FocusRequester
//import androidx.compose.ui.focus.focusRequester
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.ytmusicclone.TodoItem
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import ytmusicclone.navigation.AppNavigation
//import ytmusicclone.viewmodels.Todo
//import ytmusicclone.viewmodels.TodoViewModel
//import kotlin.time.Duration.Companion.seconds
//
//@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun TodoApp(modifier: Modifier) {
//    Scaffold(
//        topBar = {
//            Column(modifier = Modifier.background(MaterialTheme.colorScheme.primary).padding(top = 44.dp, start = 8.dp, end = 8.dp, bottom = 12.dp)) {
//                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
//                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier) {
//                        Image(modifier = Modifier.width(50.dp), painter = painterResource(R.drawable.youtube_music_logo), contentDescription = "Youtube music logo")
//                        Text("Music", color = Color.White)
//                    }
//                    Spacer(Modifier.weight(1f))
//                    Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
//                        IconButton(onClick = {}) {
//                            Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Color.White)
//                        }
//                        IconButton(onClick = {}) {
//                            Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
//                        }
//                        Button(modifier = Modifier.size(36.dp), onClick = {}, contentPadding = PaddingValues(0.dp), shape = CircleShape, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
//                            Image(modifier = Modifier.size(45.dp), painter = painterResource(R.drawable.ic_launcher_foreground), contentDescription = "Notifications")
//                        }
//                    }
//                }
//                LazyRow(modifier = Modifier.fillMaxWidth()) {
//                    items(AppNavigation.homePageTopNavigations, {it.key}){ navItem ->
//                        Button(
//                            shape = RoundedCornerShape(8.dp),
//                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
//                            onClick = {},
//                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
//                        ) {
//                            Text("${navItem.title}")
//                        }
//                        Spacer(Modifier.width(12.dp))
//                    }
//                }
//            }
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = modifier
//                .fillMaxSize()
//                .padding(top = paddingValues.calculateTopPadding() - 36.dp, bottom = paddingValues.calculateBottomPadding())
//                //.imePadding()
//                .background(Color.White)
//        ) {
//            val todoViewModel: TodoViewModel = viewModel()
//            val todos by todoViewModel.todos.collectAsState()
//            LazyColumn(modifier = Modifier.weight(1f)) {
//                itemsIndexed(todos, key = { index,item -> item.key }) { index,item ->
//                    val isLast = item.key == todos.last().key
//                    TodoItem(item, {checked, text, setInitialRender->
//                        todoViewModel.updateItem(item, checked, text, setInitialRender)
//                    }){
//                        if (isLast) { //Only create a new item on next when the current item is the last item
//                            val focusRequester = FocusRequester()
//                            todoViewModel.addNewTodo(
//                                Todo(
//                                    checked = false,
//                                    text = "",
//                                    key = index + 1,
//                                )
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun LazyItemScope.TodoItem(item: Todo, updateItem: (Boolean?, String?, Boolean?)->Unit, onNext: ()->Unit){
//    Log.d("tyui", "updated")
//    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.focusGroup().animateItem().background(item.color)) {
//        Spacer(Modifier.width(20.dp))
//        Text(item.key.toString(), modifier = Modifier.weight(0.2f))
//        Checkbox(
//            modifier = Modifier.weight(0.2f),
//            checked = item.checked,
//            onCheckedChange = {
//                updateItem(it, null, null)
//            })
//        TodoTextBox(item, updateItem, onNext )
//    }
//}
//
//@Composable
//fun RowScope.TodoTextBox(item: Todo, updateItem: (Boolean?, String?, Boolean?)->Unit, onNext: () -> Unit){
//    var editText by rememberSaveable { mutableStateOf(true)}
//    val focusRequester = remember { FocusRequester() }
//    val scope = rememberCoroutineScope()
//    LaunchedEffect(item.key) {
//        if (item.setFocusOnInitialRender) {
//            updateItem(null, null, false)
//            focusRequester.requestFocus()
//        }
//    }
//    TextField(
//        shape = RoundedCornerShape(0.dp),
//        enabled = editText,
//        modifier = Modifier
//            .weight(1f)
//            .focusRequester(focusRequester),
//        value = item.text,
//        onValueChange = {
//            updateItem(null, it, null)
//        },
//        colors = TextFieldDefaults.colors(
//            focusedContainerColor = Color.Transparent,
//            unfocusedContainerColor = Color.Transparent,
//            focusedIndicatorColor = Color.Transparent,
//            unfocusedIndicatorColor = Color.Transparent,
//            disabledTextColor = Color.Red,
//            disabledContainerColor = Color.Black
//        ),
//        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
//        keyboardActions = KeyboardActions(onNext = {
//            onNext()
//        })
//    )
//    Checkbox(checked = editText, onCheckedChange = {editText = it}, modifier = Modifier.weight(0.2f))
//    IconButton(onClick = {
//        if(AppNavigation.canNavigate){
//            AppNavigation.canNavigate = false
//            scope.launch {
//                AppNavigation.snackbarState.showSnackbar("Welcome to page ${item.key}")
//            }.apply {
//                invokeOnCompletion {
//                    AppNavigation.topBackStack.add(Destinations.DetailScreen(item.key))
//                    scope.launch {
//                        delay(2.seconds)
//                        AppNavigation.canNavigate = true
//                    }
//                }
//            }
//        }
//    }) {
//        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
//    }
//}

//@Composable
//fun DetailPage(id: Int) {
//    val todoViewModel: TodoViewModel = viewModel()
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//        , horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        val item = todoViewModel.getTodoItem(id)
//        Text("${item?.key}", fontSize = 24.sp)
//        Text("${item?.checked}", fontSize = 24.sp)
//        Text("${item?.text}", fontSize = 24.sp)
//        Text("${item?.setFocusOnInitialRender}", fontSize = 24.sp)
//    }
//}

//class TodoViewModel : ViewModel() {
//    @OptIn(ExperimentalUuidApi::class)
//    private val _todos = MutableStateFlow((0..50).map { (Todo(key = it, setFocusOnInitialRender = false)) })
//    val todos = _todos.asStateFlow()
//
//    fun getTodoItem(key: Int): Todo?{
//        return _todos.value.find { key == it.key }
//    }
//    fun addNewTodo(todo: Todo) {
//        _todos.update { list ->
//            list + todo
//        }
//    }
//
//    fun randomiseItems(){
//        viewModelScope.launch(Dispatchers.Default) {
//            for(i in 1 .. 15){
//                delay(1.seconds)
//                _todos.update { list ->
//                    list.toMutableList().randomiseList()
//                }
//            }
//        }
//    }
//
//    fun <T> MutableList<T>.randomiseList(): MutableList<T>{
//        val sizeOfList = size
//        for(i in 0 until sizeOfList){
//            val index = Random.nextInt(sizeOfList)
//            val temp = this[index]
//            this[index] = this[i]
//            this[i] = temp
//        }
//        return this
//    }
//
//    fun updateItem(item: Todo, checked: Boolean? = null, text: String? = null, setFocusOnInitialRender: Boolean? = null){
//        val index = _todos.value.indexOfFirst { item.key == it.key }
//        if(index >= 0){
//            val newItem = _todos.value[index].copy(
//                checked = checked?: item.checked,
//                text = text ?: item.text,
//                setFocusOnInitialRender = setFocusOnInitialRender?: item.setFocusOnInitialRender
//            )
//            _todos.update {list ->
//                list.toMutableList().apply {
//                    this[index] = newItem
//                }
//            }
//        }
//    }
//}
//
//@Immutable data class Todo(
//    val checked: Boolean = if (Random.nextDouble() > 0.5) true else false,
//    val text: String = "A random text",
//    val color: Color = Color(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255)),
//    val key: Int,
//    val setFocusOnInitialRender: Boolean = true
//)
