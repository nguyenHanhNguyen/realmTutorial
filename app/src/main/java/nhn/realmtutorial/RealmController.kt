package nhn.realmtutorial

import android.app.Activity
import android.app.Application
import android.app.Fragment
import io.realm.Realm
import io.realm.RealmResults
import nhn.realmtutorial.model.Book





/**
 * Created by nguyennguyen on 29/8/17.
 */

class RealmController(application: Application) {

    lateinit var instance: RealmController

    var realm: Realm

    init {
        realm = Realm.getDefaultInstance()
    }

    fun with(fragment: Fragment): RealmController {
        if (instance == null) {
            instance = RealmController(fragment.activity.application);
        }
        return instance
    }

    fun with(activity: Activity): RealmController {
        if (instance == null) {
            instance = RealmController(activity.application)
        }
        return instance
    }

    fun with(application: Application): RealmController {
        if (instance == null) {
            instance = RealmController(application)
        }
        return instance
    }

    fun refresh() {
        realm.refresh()
    }

    fun clearAll() {
        realm.beginTransaction()
        realm.clear(Book::class.java)
        realm.commitTransaction()
    }

    fun getBooks() : RealmResults<Book> {
        return realm.where(Book::class.java).findAll()
    }

    fun getBook(id: String): Book {

        return realm.where(Book::class.java).equalTo("id", id).findFirst()
    }

    fun hasBooks(): Boolean {

        return !realm.allObjects(Book::class.java).isEmpty()
    }

    //query example
    fun queryedBooks(): RealmResults<Book> {

        return realm.where(Book::class.java)
                .contains("author", "Author 0")
                .or()
                .contains("title", "Realm")
                .findAll()

    }

}