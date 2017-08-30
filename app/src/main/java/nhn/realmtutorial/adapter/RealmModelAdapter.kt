package nhn.realmtutorial.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import io.realm.RealmBaseAdapter
import io.realm.RealmObject
import io.realm.RealmResults

/**
 * Created by nguyennguyen on 30/8/17.
 */
open class RealmModelAdapter<T : RealmObject>(context: Context, realmResults: RealmResults<T>, automaticUpdate: Boolean)
    : RealmBaseAdapter<T>(context, realmResults, automaticUpdate) {


    override fun getView(position: Int, convertView: View, parent: ViewGroup): View? {
        return null
    }

}