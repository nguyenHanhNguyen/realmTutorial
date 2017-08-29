package nhn.realmtutorial.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

/**
 * Created by nguyennguyen on 29/8/17.
 */
@RealmClass
class Book : RealmObject() {

    open var id: Int = 0

    open var title: String? = null

    open var description: String? = null

    open var imageUrl: String? = null


}