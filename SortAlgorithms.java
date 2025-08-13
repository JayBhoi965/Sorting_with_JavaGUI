 class SortAlgorithms {
    public static void bubbleSort(int[] arr, BarPanel panel) throws InterruptedException {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    panel.updateArray(arr.clone());
                    Thread.sleep(1);
                }
            }
        }
    }

    public static void selectionSort(int[] arr, BarPanel panel) throws InterruptedException {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) min = j;
            }
            swap(arr, i, min);
            panel.updateArray(arr.clone());
            Thread.sleep(1);//delay
        }
    }

    public static void insertionSort(int[] arr, BarPanel panel) throws InterruptedException {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                panel.updateArray(arr.clone());
                Thread.sleep(1);//del
            }
            arr[j + 1] = key;
            panel.updateArray(arr.clone());
        }
    }

    public static void mergeSort(int[] arr, BarPanel panel) throws InterruptedException {
        mergeSortHelper(arr, 0, arr.length - 1, panel);
    }

    private static void mergeSortHelper(int[] arr, int left, int right, BarPanel panel) throws InterruptedException {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortHelper(arr, left, mid, panel);
            mergeSortHelper(arr, mid + 1, right, panel);
            merge(arr, left, mid, right);
            panel.updateArray(arr.clone());
            Thread.sleep(1);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            arr[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static void quickSort(int[] arr, BarPanel panel) throws InterruptedException {
        quickSortHelper(arr, 0, arr.length - 1, panel);
    }

    private static void quickSortHelper(int[] arr, int low, int high, BarPanel panel) throws InterruptedException {
        if (low < high) {
            int pi = partition(arr, low, high);
            panel.updateArray(arr.clone());
            Thread.sleep(1);//delay
            quickSortHelper(arr, low, pi - 1, panel);
            quickSortHelper(arr, pi + 1, high, panel);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
} 

