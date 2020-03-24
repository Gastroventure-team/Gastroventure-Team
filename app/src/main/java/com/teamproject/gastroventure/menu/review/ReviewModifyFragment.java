package com.teamproject.gastroventure.menu.review;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.otto.Subscribe;
import com.teamproject.gastroventure.MainActivity;
import com.teamproject.gastroventure.R;
import com.teamproject.gastroventure.adapter.ReviewAdapter;
import com.teamproject.gastroventure.adapter.ReviewInsertImgAdapter;
import com.teamproject.gastroventure.event.ActivityResultEvent;
import com.teamproject.gastroventure.util.BusProvider;
import com.teamproject.gastroventure.vo.ReviewInsertImgVo;
import com.teamproject.gastroventure.vo.ReviewVo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ReviewModifyFragment extends Fragment {
    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 2;

    private static final String SELECT_KEY = "select_key";
    private final String CHILE_NAME = "Review";
    private final String TAG = "ReviewInsertFrag";

    private View view;

    private MainActivity main;

    private FirebaseDatabase reviewDatabase;
    private DatabaseReference databaseReference;

    private EditText et_modify_store_name;
    private EditText et_modify_menu;
    private EditText et_modify_review_content;

    private RatingBar review_modify_rating;

    private RecyclerView modify_food_rcv_view;
    private RecyclerView.Adapter reviewModifyAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<ReviewVo> reviewList = new ArrayList<ReviewVo>();
    private ArrayList<ReviewInsertImgVo> reviewImageList = new ArrayList<ReviewInsertImgVo>();

    private Button btn_modify_image_add;
    private Button btn_review_modify;
    private Button btn_review_modify_cancel;

    private Double rating_num = 0.0;
    private String mCurrentPhotoPath;
    private String select_key;

    private Uri imgUri, photoURI;
    private File tempFile;

    public static ReviewModifyFragment newInstance(String select_key) {
        ReviewModifyFragment fragment = new ReviewModifyFragment();
        Bundle args = new Bundle();
        args.putString(SELECT_KEY, select_key);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            select_key = getArguments().getString(SELECT_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_review_modify_form, container, false);

        main = (MainActivity) getActivity();
        Log.d(SELECT_KEY, "key = " + select_key);

        et_modify_store_name = view.findViewById(R.id.review_modify_name);
        et_modify_menu = view.findViewById(R.id.review_modify_menu);
        et_modify_review_content = view.findViewById(R.id.review_modify_content);

        review_modify_rating = view.findViewById(R.id.review_modify_rating);

        modify_food_rcv_view = view.findViewById(R.id.modify_rcv_image); // 아디 연결
        modify_food_rcv_view.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        layoutManager = new GridLayoutManager(getContext(), 3);
        modify_food_rcv_view.setLayoutManager(layoutManager);

        btn_modify_image_add = view.findViewById(R.id.review_modify_image);
        btn_review_modify = view.findViewById(R.id.review_modify_btn);
        btn_review_modify_cancel = view.findViewById(R.id.review_modify_cancel_btn);

        reviewDatabase = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = reviewDatabase.getReference(); // DB 테이블 연결

        dataRead();

        review_modify_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rating_num = Double.parseDouble(String.valueOf(rating));
            }
        });

        btn_modify_image_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_review_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String store_name = et_modify_store_name.getText().toString();
                String menu = et_modify_menu.getText().toString();
                String review_content = et_modify_review_content.getText().toString();

                if (store_name.isEmpty()) {
                    Toast.makeText(getContext(), "상호명을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (menu.isEmpty()) {
                    Toast.makeText(getContext(), "메뉴를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (review_content.isEmpty()) {
                    review_content = "";
                }

                if (rating_num == 0.0) {
                    Toast.makeText(getContext(), "별점은 1점 이상 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                ReviewVo reviewVo = new ReviewVo();
                reviewVo.setStore_name(store_name);
                reviewVo.setMenu(menu);
                reviewVo.setReview_content(review_content);
                reviewVo.setRating_num(rating_num);


                databaseReference.child("Review").child(select_key).push().setValue(reviewVo); // child 는 컬럼의 기본키?

                main.replaceFragment(new ReviewFragment());
            }
        });

        btn_review_modify_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.replaceFragment(new ReviewFragment());
            }
        });

        return view;
    }

    public void dataRead() {
        databaseReference.child(CHILE_NAME).child(select_key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ReviewVo reviewVo = dataSnapshot.getValue(ReviewVo.class); // 만들어뒀던 User 객체에 데이터를 담는다.
                reviewVo.setReview_key(dataSnapshot.getKey());

                et_modify_store_name.setText(reviewVo.getStore_name());
                et_modify_menu.setText(reviewVo.getMenu());
                et_modify_review_content.setText(reviewVo.getReview_content());

                review_modify_rating.setRating((float) reviewVo.getRating_num());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 디비를 가져오던중 에러 발생 시
                Log.e("ReviewFragment", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });
    }

    public void imageSelect() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
        alertBuilder.setTitle("실행할 메뉴를 선택하세요.");

        final ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.select_dialog_singlechoice);
        adapter.add("카메라");
        adapter.add("앨범");

        alertBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                switch (id) {
                    case 0:
                        takePhoto();
                        break;
                    case 1:
                        selectAlbum();
                        break;
                }
            }
        });

        alertBuilder.show();
    }

    public void takePhoto() {
        // 촬영 후 이미지 가져옴
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (photoFile != null) {
                    Uri providerURI = FileProvider.getUriForFile(getContext(), getActivity().getPackageName(), photoFile);
                    imgUri = providerURI;
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, providerURI);
                    startActivityForResult(intent, PICK_FROM_CAMERA);
                }
            }
        } else {
            Log.v("알림", "저장공간에 접근 불가능");
            return;
        }
    }

    public File createImageFile() throws IOException {
        String imgFileName = System.currentTimeMillis() + ".jpg";
        File imageFile = null;
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/Pictures", "review");

        if (!storageDir.exists()) {
            //없으면 만들기
            Log.v("알림", "storageDir 존재 x " + storageDir.toString());
            storageDir.mkdirs();
        }
        Log.v("알림", "storageDir 존재함 " + storageDir.toString());
        imageFile = new File(storageDir, imgFileName);
        mCurrentPhotoPath = imageFile.getAbsolutePath();

        return imageFile;
    }

    public void selectAlbum() {
        //앨범에서 이미지 가져옴
        //앨범 열기
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    public void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
        Toast.makeText(getContext(), "사진이 저장되었습니다", Toast.LENGTH_SHORT).show();
    }

    public void onUriListAdd() {
        ReviewInsertImgVo reviewInsertImgVo = new ReviewInsertImgVo("", mCurrentPhotoPath);
        reviewImageList.add(reviewInsertImgVo);

        Log.d(TAG, "" + reviewImageList.size());

        modify_food_rcv_view.setAdapter(new ReviewInsertImgAdapter(getContext(), reviewImageList));

        mCurrentPhotoPath = null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onDestroyView() {
        BusProvider.getInstance().unregister(this);
        super.onDestroyView();

    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onActivityResultEvent(@NonNull ActivityResultEvent event) {
        onActivityResult(event.getRequestCode(), event.getResultCode(), event.getData());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != getActivity().RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case PICK_FROM_ALBUM: {
                //앨범에서 가져오기
                if (data.getData() != null) {
                    try {
                        photoURI = data.getData();
                        mCurrentPhotoPath = String.valueOf(photoURI);
                        //Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), photoURI);
                        onUriListAdd();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case PICK_FROM_CAMERA: {
                //카메라 촬영
                try {
                    Log.v("알림", "FROM_CAMERA 처리");
                    galleryAddPic();
                    onUriListAdd();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
