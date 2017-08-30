package nhn.realmtutorial.adapter

import android.content.Context
import io.realm.RealmResults
import nhn.realmtutorial.model.Book

/**
 * Created by nguyennguyen on 30/8/17.
 */
class RealmBookAdapter(context: Context, realmResults: RealmResults<Book>, automaticUpdate: Boolean)
    : RealmModelAdapter<Book>(context, realmResults, automaticUpdate)
