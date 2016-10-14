package co.com.gmrtrd.andmil.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class list_objects extends Fragment {
    public  List<ObjetoInfo> objetcs;
    private DatabaseReference mDatabase;
    private RecyclerView mRecyclerView;
    private RVAdapter mAdapter;
    private String TAG ="list_objetcs.java";
    ChildEventListener childEventListener;


    public list_objects() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  thisview=inflater.inflate(R.layout.fragment_list_objects, container, false);
        mRecyclerView=(RecyclerView)thisview.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setHasFixedSize(true);

        objetcs= new ArrayList<>();
        mAdapter = new RVAdapter(objetcs);
        mRecyclerView.setAdapter(mAdapter);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        /*ValueEventListener getList=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post

                    ObjetoInfo objeto = postSnapshot.getValue(ObjetoInfo.class);
                    if(!objetcs.contains(objeto)){
                        objetcs.add(objeto);
                        mAdapter.notifyItemChanged(objetcs.size()-1);
                    }


                }

                mAdapter = new RVAdapter(objetcs);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabase.addValueEventListener(getList);
        mDatabase.removeEventListener(getList);*/
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

                // A new comment has been added, add it to the displayed list
                ObjetoInfo obj = dataSnapshot.getValue(ObjetoInfo.class);
                objetcs.add(obj);
                mAdapter.notifyItemChanged(objetcs.size()-1);
                mRecyclerView.setAdapter(mAdapter);

                // ...
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so displayed the changed comment.
                ObjetoInfo newComment = dataSnapshot.getValue(ObjetoInfo.class);
                String commentKey = dataSnapshot.getKey();
                objetcs.add(newComment);
               // objetcs.set(Integer.parseInt(commentKey),newComment);

                // ...
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so remove it.
                String commentKey = dataSnapshot.getKey();
                int position=Integer.parseInt(commentKey);
                objetcs.remove(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, objetcs.size());



                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
                ObjetoInfo movedComment = dataSnapshot.getValue(ObjetoInfo.class);
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                //Toast.makeText(getContext(), "Failed to load object.",
                       // Toast.LENGTH_SHORT).show();
                mDatabase.removeEventListener(childEventListener);
            }
        };
        mDatabase.addChildEventListener(childEventListener);



        return thisview;
    }

    @Override
    public void onDestroyView() {
        mDatabase.removeEventListener(childEventListener);
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        mDatabase.onDisconnect();
        super.onStop();
    }
}
