package nathapong.dev.alllogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignedInActivity extends AppCompatActivity {

    TextView txtName, txtUId;
    Button btnLogOut;


    FirebaseAuth myFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);

        txtName = (TextView)findViewById(R.id.txtName);
        txtUId = (TextView)findViewById(R.id.txtUId);
        btnLogOut = (Button)findViewById(R.id.btnLogOut);


        myFirebaseAuth = FirebaseAuth.getInstance();

        if (myFirebaseAuth.getCurrentUser() == null){
            Intent intent = new Intent(SignedInActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        if (myFirebaseAuth.getCurrentUser() != null){
            txtName.setText(myFirebaseAuth.getCurrentUser().getDisplayName());
            txtUId.setText(myFirebaseAuth.getCurrentUser().getUid());

            Toast.makeText(SignedInActivity.this, "UID is : " + myFirebaseAuth.getCurrentUser().getDisplayName(),
                    Toast.LENGTH_SHORT).show();
        }

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myFirebaseAuth.signOut();

                Intent intent = new Intent(SignedInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
