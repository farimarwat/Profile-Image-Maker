### Profile Image Maker
An android library to create most professional profile images. It removes background from you rselfie and apply cartoon effect as well

**Note:More variants of Cartoon Effects will be added soon**

<img src="https://github.com/farimarwat/Profile-Image-Maker/blob/master/demo.png" width="40%" height="40%"/>

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

First apply cartoon effect and then remove background. If you interchange the sequence then you will loose transparency. 

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){
            mBitmap = MediaStore.Images.Media.getBitmap(contentResolver,it)
            mBitmap?.let { src ->
                mPim.applyCartoonEffect01(src){ result ->
                    mPim.applyRemoveBackground(result){ res ->
                        binding.progressBar.visibility = View.GONE
                        binding.pim.setImage(res)
                    }
                }
            }
        }
        binding.button.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            getContent.launch("image/*")
        }



**Note: Cartoon effect applicatiion requires image dimension of 1:1 (square). If you feed in an image which is not squared, it will be skewed.**
