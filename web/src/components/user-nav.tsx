import type { Theme } from "@/@types/interfaces/preferences";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuGroup,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuPortal,
  DropdownMenuRadioGroup,
  DropdownMenuRadioItem,
  DropdownMenuSeparator,
  DropdownMenuShortcut,
  DropdownMenuSub,
  DropdownMenuSubContent,
  DropdownMenuSubTrigger,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { useAuthStore } from "@/stores/auth-store";
import { usePreferencesStore } from "@/stores/preferences-store";
import {
  LaptopMinimal,
  LogOut,
  MoonIcon,
  SunIcon,
  UserIcon,
} from "lucide-react";
import { useNavigate } from "react-router-dom";

export const UserNav = () => {
  const { clearSession } = useAuthStore();
  const { theme, setTheme } = usePreferencesStore();

  const navigate = useNavigate();
  const handleNavigateToProfile = () => navigate("/profile");

  const name = "Isaac";
  const email = "";
  const avatar = "";
  const avatarFallback = "U";

  const handleSignOut = () => clearSession();

  const handleThemeChange = (theme: string) => setTheme(theme as Theme);

  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <Button variant="ghost" className="relative h-11 w-11 rounded-full">
          <Avatar className="h-11 w-11">
            <AvatarImage src={avatar} alt={name} />
            <AvatarFallback>{avatarFallback}</AvatarFallback>
          </Avatar>
        </Button>
      </DropdownMenuTrigger>

      <DropdownMenuContent className="w-32" align="end">
        <div className="md:hidden">
          <DropdownMenuLabel className="font-normal">
            <div className="flex flex-col space-y-1">
              <p className="text-sm font-medium leading-none">{name}</p>
              <p className="text-xs leading-none text-muted-foreground">
                {email}
              </p>
            </div>
          </DropdownMenuLabel>
          <DropdownMenuSeparator />
        </div>
        <DropdownMenuGroup>
          <DropdownMenuItem onClick={handleNavigateToProfile}>
            <span>Profile</span>
            <DropdownMenuShortcut>
              <UserIcon />
            </DropdownMenuShortcut>
          </DropdownMenuItem>
        </DropdownMenuGroup>
        <DropdownMenuSeparator />
        <DropdownMenuSub>
          <DropdownMenuSubTrigger>Tema</DropdownMenuSubTrigger>
          <DropdownMenuPortal>
            <DropdownMenuSubContent>
              <DropdownMenuRadioGroup
                value={theme}
                onValueChange={handleThemeChange}
              >
                <DropdownMenuRadioItem value="light">
                  Claro
                  <DropdownMenuShortcut>
                    <SunIcon />
                  </DropdownMenuShortcut>
                </DropdownMenuRadioItem>
                <DropdownMenuRadioItem value="dark">
                  Escuro
                  <DropdownMenuShortcut>
                    <MoonIcon />
                  </DropdownMenuShortcut>
                </DropdownMenuRadioItem>
                <DropdownMenuRadioItem value="system">
                  Sistema
                  <DropdownMenuShortcut>
                    <LaptopMinimal />
                  </DropdownMenuShortcut>
                </DropdownMenuRadioItem>
              </DropdownMenuRadioGroup>
            </DropdownMenuSubContent>
          </DropdownMenuPortal>
        </DropdownMenuSub>
        <DropdownMenuSeparator />
        <DropdownMenuItem onClick={handleSignOut}>
          <span>Sign out</span>
          <DropdownMenuShortcut>
            <LogOut />
          </DropdownMenuShortcut>
        </DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  );
};
