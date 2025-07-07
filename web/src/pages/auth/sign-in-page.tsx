import { signInSchema, type SignInSchema } from "@/@types/schemas/auth";
import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import {
  Form,
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input, SecureInput } from "@/components/ui/input";
import { useSignIn } from "@/hooks/auth/use-sign-in";
import { useAuthStore } from "@/stores/auth-store";
import { zodResolver } from "@hookform/resolvers/zod";
import { LoaderCircleIcon } from "lucide-react";
import { useForm } from "react-hook-form";
import { Link, useLocation, useNavigate } from "react-router-dom";

export const SignInPage = () => {
  const { setSession } = useAuthStore();
  const { isPending, mutateAsync } = useSignIn();

  const location = useLocation();
  const navigate = useNavigate();

  const form = useForm<SignInSchema>({
    resolver: zodResolver(signInSchema),
    defaultValues: {
      email: location.state?.email ?? "",
      password: "",
    },
  });

  const handleFormSubmit = async (values: SignInSchema) => {
    const response = await mutateAsync(values);
    console.log({ response });
    if (!response) return;

    setSession(response);
    navigate("/", { replace: true });
  };

  return (
    <Card className="w-full max-w-sm">
      <CardHeader>
        <CardTitle>Sign in to your account</CardTitle>
        <CardDescription>
          Enter your email below to login to your account
        </CardDescription>
      </CardHeader>

      <CardContent className="flex flex-col gap-4">
        <Form {...form}>
          <form
            className="flex flex-col gap-4"
            onSubmit={form.handleSubmit(handleFormSubmit)}
          >
            <FormField
              control={form.control}
              name="email"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Email</FormLabel>
                  <FormControl>
                    <Input placeholder="john@doe.com" {...field} />
                  </FormControl>
                  <FormDescription />
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="password"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Password</FormLabel>
                  <FormControl>
                    <SecureInput
                      placeholder="Password"
                      type="password"
                      {...field}
                    />
                  </FormControl>
                  <FormDescription />
                  <FormMessage />
                </FormItem>
              )}
            />

            <Button disabled={isPending} type="submit" className="w-full">
              {isPending && <LoaderCircleIcon className="animate-spin" />}
              <>Sign In</>
            </Button>
          </form>
        </Form>

        <div className="flex items-center justify-center">
          <span>Don&apos;t have an account?</span>
          <Button type="button" className="p-2" variant="link" asChild>
            <Link to="/auth/sign-up">Sign up</Link>
          </Button>
        </div>
      </CardContent>
    </Card>
  );
};
