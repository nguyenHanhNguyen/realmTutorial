package nhn.realmtutorial.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import io.realm.Realm
import nhn.realmtutorial.R
import nhn.realmtutorial.RealmController
import nhn.realmtutorial.model.Book


/**
 * Created by nguyennguyen on 30/8/17.
 */
class BooksAdapter(context: Context) : RealmRecyclerViewAdapter<Book>() {

    var context: Context = context
    lateinit var realm: Realm
    lateinit var inflater: LayoutInflater
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_book, parent, false)
        return CardViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        realm = RealmController.getInstance().realm


        // get the article
        val book = getItem(position)
        // cast the generic view holder to our specific one
        val holder = holder as CardViewHolder

        // set the title and the snippet
        holder.textTitle.setText(book.title)
        holder.textAuthor.setText(book.author)
        holder.textDescription.setText(book.description)

        // load the background image
        if (book.imageUrl != null) {
            Glide.with(context)
                    .load(book.imageUrl!!.replace("https", "http"))
                    .asBitmap()
                    .fitCenter()
                    .into(holder.imageBackground)
        }


        holder.card.setOnLongClickListener {
            val results = realm.where(Book::class.java).findAll()

            // Get the book title to show it in toast message
            val b = results[position]
            val title = b.title

            // All changes to data must happen in a transaction
            realm.beginTransaction()

            // remove single match
            results.removeAt(position)
            realm.commitTransaction()

//            if (results.size == 0) {
//                Prefs.with(context).setPreLoad(false)
//            }

            notifyDataSetChanged()

            Toast.makeText(context, title + " is removed from Realm", Toast.LENGTH_SHORT).show()
            false
        }

        //update single match from realm
        holder.card.setOnClickListener {
            inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val content = inflater.inflate(R.layout.edit_item, null)
            val editTitle = content.findViewById(R.id.title) as EditText
            val editAuthor = content.findViewById(R.id.author) as EditText
            val editThumbnail = content.findViewById(R.id.thumbnail) as EditText

            editTitle.setText(book.title)
            editAuthor.setText(book.author)
            editThumbnail.setText(book.imageUrl)

            val builder = AlertDialog.Builder(context)
            builder.setView(content)
                    .setTitle("Edit Book")
                    .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { dialog, which ->
                        val results = realm.where(Book::class.java).findAll()

                        realm.beginTransaction()
                        results[position].author = (editAuthor.text.toString())
                        results[position].title = (editTitle.text.toString())
                        results[position].imageUrl = (editThumbnail.text.toString())

                        realm.commitTransaction()

                        notifyDataSetChanged()
                    })
                    .setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            val dialog = builder.create()
            dialog.show()
        }


    }


    override fun getItemCount(): Int {
        if(realmAdapter != null) {
            return realmAdapter!!.count
        }
        return 0
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var card: CardView
        var textTitle: TextView
        var textAuthor: TextView
        var textDescription: TextView
        var imageBackground: ImageView

        init {
            card = itemView.findViewById(R.id.card_books) as CardView
            textTitle = itemView.findViewById(R.id.text_books_title) as TextView
            textAuthor = itemView.findViewById(R.id.text_books_author) as TextView
            textDescription = itemView.findViewById(R.id.text_books_description) as TextView
            imageBackground = itemView.findViewById(R.id.image_background) as ImageView
        }
    }

}