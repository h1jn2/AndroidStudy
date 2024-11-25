package com.example.kotlinstudy

import android.os.Bundle
import android.os.FileObserver.DELETE
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)


        // 기본적으로 데이터베이스 작업은 메인쓰레드에서 할 수 없음 (작업하는 동안 사용자가 기다려야 하기 때문)
        // 쓰레드를 이용하거나 Async를 이용
        val database = Room.databaseBuilder(
            applicationContext,
            UserDataBase::class.java,
            "user-database"
        ).allowMainThreadQueries().build()

        findViewById<TextView>(R.id.save).setOnClickListener {
            val userProfile = UserProfile(1, "홍", "길동")
            database.userProfileDao().insert(userProfile)
        }
        findViewById<TextView>(R.id.load).setOnClickListener {
            val userProfiles = database.userProfileDao().getAll()

            userProfiles.forEach {
                Log.d("testt", "" + it.id + it.firstName)
            }
        }
        findViewById<TextView>(R.id.delete).setOnClickListener {
            database.userProfileDao().delete(1)
        }
    }
}

@Database(entities = [UserProfile::class], version = 1)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
}

@Entity
class UserProfile(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "first_name")
    val firstName: String
) {
    constructor(lastName: String, firstName: String) : this(0, lastName, firstName)
}

@Dao
interface UserProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userProfile: UserProfile)

    @Query ("DELETE FROM userprofile WHERE id = :userId" )
    fun delete(userId: Int)

    @Query("SELECT * FROM userprofile")
    fun getAll(): List<UserProfile>
}