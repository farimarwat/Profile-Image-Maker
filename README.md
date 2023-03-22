### Profile Image Maker
An android library to create most professional profile images. It removes background from you rselfie and apply cartoon effect as well


Sample app is included. Clone the project and open in android studio:

```
git clone https://github.com/farimarwat/Profile-Image-Maker.git
```

<img src="https://github.com/farimarwat/Profile-Image-Maker/blob/master/demo.png" width="40%" height="40%"/>

### More Cartoon Effects

<img src="https://github.com/farimarwat/Profile-Image-Maker/blob/master/pim_github.jpg" width="40%" height="40%"/>

#### Caution: If you want to get effect like below images then you are not supposed to be here. Below are cartoon effects created by StyleGan2 (by NVIDIA) and getting effects like this requires more resources. So try to do steps below for such market competitive results:
- Learn Python
- Purchase Google Cloud computing
- Create API to get the target image
- Process the image on server by StyleGan2
- Post back the cartoonized image

#### Requirements for StyleGan2
- GPU: NVIDIA GPU with at least 8GB of VRAM, preferably a GTX 1080 Ti or better
- RAM: At least 16GB of RAM
- Disk space: Several hundred gigabytes of disk space to store the dataset and the model checkpoin
- Operating system: Linux or Windows 10 with Windows Subsystem for Linux (WSL)
- Software: CUDA Toolkit, cuDNN, TensorFlow, and optionally, PyTorch or other deep learning frameworks

#### What I do not offer:
<img src="https://github.com/farimarwat/Profile-Image-Maker/blob/master/stylegan2.png" width="40%" height="40%"/>


### Implementation
```
  implementation 'io.github.farimarwat:profileimagemaker:1.2'
  ```

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
**Note: There are two view of profile image. First one is for transparent image and second is for full image**

```
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
				```
        





```
      <pk.farimarwat.profileimagemaker.ProfileImageView2
            android:id="@+id/pim"
            android:layout_width="300dp"
            android:layout_height="400dp"
            app:pim2_backgroundcolor="@color/background"
            app:pim2_borderwidth="20"
            app:pim2_bordercolor="@color/border"
            app:pim2_imagesize="600"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            ></pk>
			```

#### Traditional Cartoon Effect:

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
#### Premium Cartoon Effect:

```
mBitmap?.let { src ->
                mPim.applyCartoonEffectPremium(mContext, 8,src,object : ToonListener {
                    override fun onError(error: String) {
                        Log.e(TAG,"Error: $error")
                    }

                    override fun onSuccess(bitmap: Bitmap) {
                        binding.progressBar.visibility = View.GONE
                        binding.pim.setImage(bitmap)
                    }

                })
```

**applyCartoonEffectPremium()** methods takes 4 Params:
- Context
- Effect Type (0 to 8)
- Target bitmap
- Listener

#### Change Log
**v1.2:**
- 8 cartoon effects added
- One another image view (ProfileImageView2) added

**v1.1:**

1 cartoon effect added

**v 1.0:**

Initial release with image's background remover
