package cp.com.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


public class MyAIDLService extends Service {
    private  static  final String TAG=MyAIDLService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return  mBinder;
    }

    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public String getStr() throws RemoteException {
            Log.e(TAG,"this from AIDL service ...getStr..");
            return TAG;
        }

        @Override
        public boolean setStatus(int aInt) throws RemoteException {
            Log.e(TAG,"this from AIDL service ...setStatus..="+aInt);
            return (aInt%2 == 0);
        }
    };
}
