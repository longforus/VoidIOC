package com.longforus.test.a10_voidioc;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @VoidIocId(R.id.tv_show)
    private TextView tv_show;
    @VoidIocId(R.id.btn_go)
    private Button btn_go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VoidIoc.inject(this);
    }
    @VoidIocClick(R.id.btn_go)
    public void toClick(View view) {
        tv_show.setText("are you ok?");
    }
}
