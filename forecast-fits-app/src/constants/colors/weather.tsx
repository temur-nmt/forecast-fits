export type WeatherColors = {
    background: string;
    text: string;
    box: string;
};

export const weatherTheme: Record<string, WeatherColors> = {
    dry: {
        background: '#0ea5e9',
        text: '#ffffff',
        box: '#0284c7',
    },
    fog: {
        background: '#64748b',
        text: '#ffffff',
        box: '#475569',
    },
    rain: {
        background: '#3b82f6',
        text: '#ffffff',
        box: '#2563eb',
    },
    sleet: {
        background: '#818cf8',
        text: '#ffffff',
        box: '#6366f1',
    },
    snow: {
        background: '#f1f5f9',
        text: '#0f172a',
        box: '#e2e8f0',
    },
    hail: {
        background: '#cbd5e1',
        text: '#0f172a',
        box: '#94a3b8',
    },
    thunderstorm: {
        background: '#312e81',
        text: '#ffffff',
        box: '#1e1b4b',
    },
    null: {
        background: '#334155',
        text: '#ffffff',
        box: '#1e293b',
    },
};