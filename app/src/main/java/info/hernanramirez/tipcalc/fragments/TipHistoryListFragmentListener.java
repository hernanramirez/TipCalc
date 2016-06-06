package info.hernanramirez.tipcalc.fragments;

import info.hernanramirez.tipcalc.models.TipRecord;

/**
 * Created by hernanr on 6/4/16.
 */
public interface TipHistoryListFragmentListener {
    void addToList(TipRecord record);
    void clearList();
}
