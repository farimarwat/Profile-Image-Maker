### Profile Image Maker
An android library to create most professional profile images

**Note:Cartoonify Effect will come soon**

### Implementation

        implementation 'io.github.farimarwat:profileimagemaker:1.0'

### Usage
#### Step 1:
First Create Profile Image Maker Instance:

    lateinit var mPim: ProfileImageMaker
        
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(binding.root)
            mContext = this
            mPim = ProfileImageMaker.Builder(mContext)
                .build()
    			.....
    }

#### Step 2

Place ProfileImageView in your xml file:

    <pk.farimarwat.profileimagemaker.ProfileImageView
            android:id="@+id/pim"
            android:layout_width="300dp"
            android:layout_height="400dp"
            app:pim_backgroundcolor="@color/background"
            app:pim_borderwidth="20"
            app:pim_bordercolor="@color/border"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            />

#### Final Step

Apply remove background, get the image in form of bitmap and set it to ProfileImageView :
Here "src" is bitmapt that needs to remove background and "result" is a bitmap with removed background

    mPim.applyRemoveBackground(src){ result ->
                        binding.pim.setImage(result)
                    }




