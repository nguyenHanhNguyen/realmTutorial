package nhn.realmtutorial.adapter

import android.support.v7.widget.RecyclerView
import io.realm.RealmObject
import io.realm.RealmBaseAdapter



/**
 * Created by nguyennguyen on 29/8/17.
 */
abstract class RealmRecyclerViewAdapter<T : RealmObject> : RecyclerView.Adapter<*>() {

    var realmAdapter: RealmBaseAdapter<T>? = null

    fun getItem(position: Int): T {

        return realmAdapter!!.getItem(position)
    }
}
