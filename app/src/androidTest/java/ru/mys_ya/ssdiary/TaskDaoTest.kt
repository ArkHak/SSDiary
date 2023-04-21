package ru.mys_ya.ssdiary

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.mys_ya.ssdiary.data.Task
import ru.mys_ya.ssdiary.data.db.DiaryDatabase
import ru.mys_ya.ssdiary.data.db.TaskDao
import ru.mys_ya.ssdiary.data.db.entitys.TaskEntity
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TaskDaoTest {

    private lateinit var taskDao: TaskDao
    private lateinit var diaryDatabase: DiaryDatabase
    private val task1 = Task(
        id = 1,
        dateStart = 1682327820,
        dateFinish = 1682330400,
        name = "My task",
        description = "Эрик тен Хаг после матча: «Солидное выступление, заслуженная победа. Единственное, что мне не понравилось — мы должны были забить больше. Всегда хочется забить больше и прикончить игру в самом начале. В больших матчах не получается создать большое количество моментов, поэтому нужно пользоваться теми, что имеются».\n\n• О травмах: «У нас больше 11 травмированных игроков. То, что в старте в итоге вышел Эриксен, не сделало нас хуже (улыбается). Он провёл очень хороший матч».\n\n• О Марсьяле: «Он действовал отлично. Антони невероятный игрок. Он способен играть в подыгрыше, держать мяч, он хорошо прессингует и будет продолжать забивать голы. Он выходит на той позиции, на которой должен выходить».\n\n• О крайних защитниках: «От обоих фуллбэков важно видеть такую работу в команде, чтобы они врывались в штрафную, забегали за спины и кооперировались с остальными игроками. За счёт этого мы создали много пространства, и мы также делали своевременные передачи в центральные зоны».\n\n• Об Антони дос Сантосе: «Нам нужно больше бомбардиров, и я рад выступлению Антони. Он забил гол и отдал голевую. В начале матча он был не так хорош — по-моему, дважды он принял неверное решение рядом с лицевой, когда ему нужно было отдать мяч Эриксену. Но затем он включился в игру и был постоянной угрозой для соперника.\n\nОн явно прогрессирует. Он создал много моментов против «Эвертона» и не смог забить сам, и я рад, что сегодня ему удалось это сделать. Он бы забил ещё один, если бы Уан-Биссака не заблокировал тот удар!»\n"
    )
    private val task2 = Task(
        id = 2,
        dateStart = 1682337600,
        dateFinish = 1682341200,
        name = "My task2",
        description = "Описание моего дела2"
    )

    private val task1Entity = TaskEntity(
        id = task1.id,
        dateStart = task1.dateStart,
        dateFinish = task1.dateFinish,
        name = task1.name,
        description = task1.description
    )

    private val task2Entity = TaskEntity(
        id = task2.id,
        dateStart = task2.dateStart,
        dateFinish = task2.dateFinish,
        name = task2.name,
        description = task2.description
    )

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        diaryDatabase = Room.inMemoryDatabaseBuilder(context, DiaryDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        taskDao = diaryDatabase.taskDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        diaryDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsTaskIntoDb() = runBlocking {
        addOneTaskToDb()
        val timestampDay = task1Entity.dateStart
        val allTasks = taskDao.getAllTasks(timestampDay)
        assertEquals(allTasks[0], task1Entity)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetTaskById_returnsTaskByIdFromDb() = runBlocking {
        addTwoTasksToDb()
        val id = task2Entity.id
        val taskById = taskDao.getTask(id)
        assertEquals(taskById, task2Entity)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllTasks_returnsEmptyTasksListFromDb() = runBlocking {
        addTwoTasksToDb()
        val incorrectTimestamp: Long = 1688754071
        val emptyList = listOf<TaskEntity>()
        val allTasks = taskDao.getAllTasks(incorrectTimestamp)
        assertEquals(emptyList, allTasks)
    }

    private suspend fun addOneTaskToDb() {
        taskDao.insert(task1Entity)
    }

    private suspend fun addTwoTasksToDb() {
        taskDao.insert(task1Entity)
        taskDao.insert(task2Entity)
    }
}