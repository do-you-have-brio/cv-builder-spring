import { ResumesTable } from "@/components/resumes-table";

const resumesDataMock = [
  {
    id: "1",
    name: "User Authentication Module",
    createdAt: "2024-01-15",
    type: "Component",
    application: "E-commerce Platform",
  },
  {
    id: "2",
    name: "Payment Gateway Integration",
    createdAt: "2024-01-12",
    type: "Service",
    application: "Online Store",
  },
  {
    id: "3",
    name: "Dashboard Analytics",
    createdAt: "2024-01-10",
    type: "Page",
    application: "Admin Panel",
  },
  {
    id: "4",
    name: "Email Notification System",
    createdAt: "2024-01-08",
    type: "Service",
    application: "CRM System",
  },
  {
    id: "5",
    name: "Product Catalog",
    createdAt: "2024-01-05",
    type: "Component",
    application: "E-commerce Platform",
  },
  {
    id: "6",
    name: "User Profile Management",
    createdAt: "2024-01-03",
    type: "Page",
    application: "Social Media App",
  },
  {
    id: "7",
    name: "File Upload Handler",
    createdAt: "2024-01-01",
    type: "Utility",
    application: "Document Manager",
  },
  {
    id: "8",
    name: "Search Functionality",
    createdAt: "2023-12-28",
    type: "Feature",
    application: "Knowledge Base",
  },
  {
    id: "9",
    name: "Real-time Chat",
    createdAt: "2023-12-25",
    type: "Feature",
    application: "Customer Support App",
  },
  {
    id: "10",
    name: "Inventory Management",
    createdAt: "2023-12-22",
    type: "Page",
    application: "Warehouse System",
  },
  {
    id: "11",
    name: "Notification Preferences",
    createdAt: "2023-12-20",
    type: "Component",
    application: "User Settings Panel",
  },
  {
    id: "12",
    name: "PDF Report Generator",
    createdAt: "2023-12-18",
    type: "Utility",
    application: "Reporting Tool",
  },
  {
    id: "13",
    name: "Referral Program Integration",
    createdAt: "2023-12-15",
    type: "Service",
    application: "Marketing Suite",
  },
  {
    id: "14",
    name: "Map Location Picker",
    createdAt: "2023-12-13",
    type: "Component",
    application: "Travel Booking App",
  },
  {
    id: "15",
    name: "Order Tracking Page",
    createdAt: "2023-12-10",
    type: "Page",
    application: "Delivery System",
  },
  {
    id: "16",
    name: "Language Switcher",
    createdAt: "2023-12-08",
    type: "Component",
    application: "Multi-language Platform",
  },
  {
    id: "17",
    name: "Two-Factor Authentication",
    createdAt: "2023-12-06",
    type: "Feature",
    application: "Security Module",
  },
  {
    id: "18",
    name: "Calendar Integration",
    createdAt: "2023-12-03",
    type: "Service",
    application: "Event Scheduler",
  },
  {
    id: "19",
    name: "Feedback Collection Form",
    createdAt: "2023-12-01",
    type: "Component",
    application: "User Research Tool",
  },
  {
    id: "20",
    name: "API Rate Limiter",
    createdAt: "2023-11-28",
    type: "Utility",
    application: "Backend Gateway",
  },
];

export const ResumesPage = () => {
  return (
    <div className="flex flex-col flex-1">
      <ResumesTable resumes={resumesDataMock} />
    </div>
  );
};
