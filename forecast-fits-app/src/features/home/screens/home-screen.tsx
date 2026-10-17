import { Button, Text, View } from 'react-native';
import { SafeAreaView } from "react-native-safe-area-context";
import WeatherSection from '../components/weather-section';
import RecommendationSection from '../components/recommendation-section';
import { useEffect, useState } from 'react';
import Toast from 'react-native-toast-message';
import * as Location from 'expo-location';
import { ForecastVM, WeatherVM, RecommendationVM } from '@/utils/types';

interface RecommendationResponse {
    weather: WeatherVM,
    forecast: ForecastVM[],
    recommendation: RecommendationVM
}

const HomeScreen = () => {

    const [recommendation, setRecommendation] = useState<RecommendationResponse>();
    const [failed, setFailed] = useState<boolean>(false);

    const fetchData = async (): Promise<void> => {
        try {
            let { status } = await Location.requestForegroundPermissionsAsync();
            if (status !== 'granted') {
                Toast.show({
                    type: 'error',
                    text1: 'Location access denied.',
                    text2: 'We need access to your location for the local weather data'
                });
                return;
            }

            let position = await Location.getLastKnownPositionAsync({});
    
            if (!position) {
                position = await Location.getCurrentPositionAsync({ accuracy: Location.Accuracy.Balanced });
            }
            const coords = position.coords;

            let locationGeocoded: Location.LocationGeocodedLocation = {
                latitude: coords.latitude,
                longitude: coords.longitude
            }

            let location = await Location.reverseGeocodeAsync(locationGeocoded);

            // TODO: read token from login process
            const token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBcnRodXJTY2hvcGVuaGF1ZXIxODY1IiwiaWF0IjoxNzkxNzMxNTMwLCJleHAiOjE3OTE3MzUxMzB9.yHOBgW-r3xQ0ipR6bxpt9t9jHyIfQzpBFTJWmT11bos";
            const response = await fetch(`http://192.168.178.26:8080/?lat=${coords.latitude}&lon=${coords.longitude}&location=${location[0].city}`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-type': 'application/json'
                }
            });

            if (!response.ok) {
                Toast.show({
                    type: 'error',
                    text1: 'An error occured while trying to fetch your recommendation.'
                });
                setFailed(true);
            }
            const data = await response.json() as RecommendationResponse;
            setRecommendation(data);

        } catch (err) {
            console.error("ERROR WHILE FETCHING GPS:", err);
            Toast.show({
                type: 'error',
                text1: 'A network error occured.'
            });
            setFailed(true);
        }
    };

    useEffect(() => {
        fetchData();
    }, []);

    return (
        <SafeAreaView className='flex justify-start items-center h-full w-full px-5'>
            <View className='w-full pt-4'>
                <Text className='font-bold text-4xl py-2'>Hi, PLACEHOLDER!</Text>
            </View>
            {recommendation ? (
                <>
                    <WeatherSection weather={recommendation.weather} forecast={recommendation.forecast}/>
                    <RecommendationSection {...recommendation.recommendation}/>
                </>
            ) : (
                <View className='flex flex-col justify-center items-center h-full w-full'>
                    <Text className='font-bold text-2xl py-5'>Loading data...</Text>
                   {failed && <Button title='Retry' onPress={() => fetchData()}></Button>}
                </View>
            )}
            <Toast/>
        </SafeAreaView>
    );
}

export default HomeScreen;
