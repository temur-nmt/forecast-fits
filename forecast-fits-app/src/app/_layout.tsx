import { Stack } from 'expo-router';
import { useEffect } from 'react';
import * as SplashScreen from 'expo-splash-screen';
import '../global.css';

SplashScreen.preventAutoHideAsync();

const RootLayout = () => {

  useEffect(() => {
    SplashScreen.hideAsync();
  }, []);
  
  return (
    <Stack screenOptions={{headerShown: true, animation: "fade"}}>
      <Stack.Screen name="(tabs)" options={{ headerShown: false }}/>
    </Stack>
  );
};

export default RootLayout;
