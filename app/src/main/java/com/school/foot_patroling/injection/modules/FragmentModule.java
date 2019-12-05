package com.school.foot_patroling.injection.modules;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.school.foot_patroling.injection.qualifier.ChildFragmentManager;
import com.school.foot_patroling.injection.scopes.PerFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private final Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerFragment
    @ChildFragmentManager
    FragmentManager provideChildFragmentManager() { return mFragment.getChildFragmentManager(); }


}
