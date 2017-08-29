package nhn.realmtutorial

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by nguyennguyen on 29/8/17.
 */

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val realmConfiguration : RealmConfiguration = RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build()


    }

}
