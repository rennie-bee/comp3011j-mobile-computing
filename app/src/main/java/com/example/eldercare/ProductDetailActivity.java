package com.example.eldercare;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.w3c.dom.Text;

// This activity presented detailed page of every product
public class ProductDetailActivity extends AppCompatActivity {
    // Declare two static Strings to serve the intent to pass the name and id of the image
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_IMAGE_ID = "product_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Receive the intent sent from the
        Intent intent = getIntent();
        String productName = intent.getStringExtra(PRODUCT_NAME);
        int productImageID = intent.getIntExtra(PRODUCT_IMAGE_ID, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Collapsing Toolbar Layout is provided by Design Support package.
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        // Connect the product_image with imageview in activity_product_detail.xml
        ImageView product_image = (ImageView) findViewById(R.id.product_image_view);
        // Connect the product_content_text with textview in activity_product_detail.xml
        TextView product_content_text = (TextView) findViewById(R.id.product_content_text);
        // Set the toolbar as actionbar, also supporting HomeAsUp button
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(productName);
        // Glide is a package which supports image display in a very sensational way
        // it can be referenced from URL:https://github.com/bumptech/glide
        Glide.with(this).load(productImageID).into(product_image);

        // Switch different cases, to equip each product with its explaining text content
        String content;
        switch (productName) {
            case "Thermos":
                content = "A thermos can carry some hot water, which is good for your health. Hotter water" +
                        " can fix some ailments in your stomach.";
                break;
            case "Walk Stick":
                content = "A walking stick can support you while taking for a walk. Your limb strength" +
                        " might not be as strong as that in your youth.";
                break;
            case "Wheelchair":
                content = "A wheelchair is more complex, but also more powerful than a walk stick. " +
                        " Even if your legs are unable to move, the wheelchair can be operated by yourself" +
                        " or others to help you move and turn.";
                break;
            case "Sphygmomanometer":
                content = "A sphygmomanometer (blood pressure monitor) can test your blood pressure quickly." +
                        " Some older generations are bothered by blood pressure illness , so they need a" +
                        " sphygmomanometer to monitor their blood pressure anytime.";
                break;
            case "Magnifier":
                content = "Some elders' eyesight may decrease. A magnifier can apply physics theory to " +
                        "magnify objects that it focuses on. Noteworthy, this is" +
                        " not a intellectual device so you may need to move it to get the best view.";
                break;
            case "Calcium Tablet":
                content = "Bones may be less robust with aging. A calcium tablet can supply your body some" +
                        " extra calcium element, preventing osteoporosis.";
                break;
            case "Reading Glasses":
                content = "With aging, people apt to catch a Presbyopia, which is a normal physiological phenomenon." +
                        "When looking at close objects, you must move away to see clearly. To decrease that, a pair of" +
                        "reading glasses is more helpful compared with common glasses.";
                break;
            case "First Aid Kit":
                content = "An first aid kit is applicable to all generations, especially for the elder generation." +
                        "The medical kit contains some commonly used medical supplies, such as iodine, clinical thermometers," +
                        " band-aids, etc. It can help people to deal with emergencies in many circumstances.";
                break;
            default:
                content = null;
                break;
        }
        product_content_text.setTextSize(22);
        product_content_text.setText(content);
    }

    // There should be a back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}