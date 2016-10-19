#include <jni.h>
#include <string>

//JNIEXPORT jstring JNICALL
extern "C"
jstring  Java_com_cp_jni_JavaNative_stringFromJNI(JNIEnv *env, jobject /*instance*/) {
    std::string hello = "Hello from JNI C++";
    return env->NewStringUTF(hello.c_str());
}

//extern "C"
//jstring
//Java_com_cp_MainActivity_stringFromJNI(
//        JNIEnv *env,
//        jobject /* this */) {
//    std::string hello = "Hello from C++";
//    return env->NewStringUTF(hello.c_str());
//}
