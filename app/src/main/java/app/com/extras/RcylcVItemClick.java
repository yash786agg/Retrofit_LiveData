package app.com.extras;

import android.view.View;

/*
 * Created by Yash on 14/1/18.
 */

public interface RcylcVItemClick
{
    //Act as a communicator b/w main activity and adapter to provide item click
    void onItemClick(View view, int position);
}
