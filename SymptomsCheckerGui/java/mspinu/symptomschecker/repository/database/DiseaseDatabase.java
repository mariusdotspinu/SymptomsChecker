package mspinu.symptomschecker.repository.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import mspinu.symptomschecker.repository.dao.DiseaseDao;
import mspinu.symptomschecker.repository.entities.DiseaseEntity;

@Database(entities = {DiseaseEntity.class}, version = 1)
public abstract class DiseaseDatabase extends RoomDatabase {
    private static DiseaseDatabase INSTANCE;

    public abstract DiseaseDao diseaseDao();

    public static DiseaseDatabase getDiseaseDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), DiseaseDatabase.class, "disease-database")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
