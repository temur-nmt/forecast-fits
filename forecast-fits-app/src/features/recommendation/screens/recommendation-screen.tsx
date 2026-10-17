import { ScrollView, Text } from "react-native";
import { Stack, useLocalSearchParams } from "expo-router";

const RecommendationScreen = () => {

    const { baseLayer, midLayer, outerLayer, bottom, footwear, headwear, accessoires } = useLocalSearchParams();

    return (
        <ScrollView className="rounded-3xl p-6 my-2 w-full h-full">
            <Stack.Screen options={{ title: 'Recommendation' }} />
            <Text>Welcome to the Recommendation Page</Text>
        </ScrollView>
    )
};

export default RecommendationScreen;