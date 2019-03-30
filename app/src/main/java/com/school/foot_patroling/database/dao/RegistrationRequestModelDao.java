package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.RegistrationRequestModel;
import com.school.foot_patroling.register.model.UpdatedFootPatrollingSectionsDto;

import java.util.List;

@Dao
public interface RegistrationRequestModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RegistrationRequestModel registrationRequestModel);

    @Query("DELETE FROM RegistrationRequestModel")
    void deleteAll();

    @Query("SELECT * from RegistrationRequestModel") //" ORDER BY message_id ASC")
    List<RegistrationRequestModel> getAllRegistrationRequestModels();
}
