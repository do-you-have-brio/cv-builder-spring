import type { Theme } from "@/@types/interfaces/preferences";
import { create } from "zustand";
import { createJSONStorage, persist } from "zustand/middleware";

export interface PreferencesStore {
  theme: Theme;
  setTheme: (theme: Theme) => void;
}

export const usePreferencesStore = create<PreferencesStore>()(
  persist(
    (set) => ({
      theme: "system",
      setTheme: (theme: Theme) => set({ theme }),
    }),
    {
      name: "@cv-builder::preferences",
      storage: createJSONStorage(() => localStorage),
      version: 1,
    }
  )
);
