package com.priyanshu.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity (tableName = "notesTable")
 class Note
        (@ColumnInfo(name = "Title")val noteTitle:String ,
         @ColumnInfo(name = "Description") val noteDescription:String,
         @ColumnInfo (name = "timestamp") val timestamp: String
         )
    {

            @PrimaryKey(autoGenerate = true)
            var id = 0
        }

